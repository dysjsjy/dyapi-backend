# 优化
### 代码功能与逻辑分析
该代码的功能是通过 RESTful API 获取调用次数最多的接口信息。以下是具体的执行流程和逻辑：

1. **查询用户接口调用记录**：
    - 使用 `userInterfaceInfoMapper.listTopInvokeInterfaceInfo(3)` 查询调用次数最多的 3 个接口的调用记录 (`UserInterfaceInfo`)。

2. **按接口 ID 分组**：
    - 使用 `Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId)` 将调用记录按 `InterfaceInfoId` 分组，以便后续操作中方便通过接口 ID 获取调用数据。

3. **查询接口信息**：
    - 根据分组后的接口 ID，构造 `QueryWrapper` 查询实际的接口信息 (`InterfaceInfo`)。

4. **异常处理**：
    - 如果查询结果为空，则抛出 `BusinessException`。

5. **构建返回数据**：
    - 遍历查询到的 `InterfaceInfo` 列表，将其属性复制到 `InterfaceInfoVO` 对象中。
    - 补充接口调用的总次数信息 (`TotalNum`)。
    - 最终将 `InterfaceInfoVO` 列表封装为 `BaseResponse` 并返回。

---

### 为什么这样写？
1. **分层设计**：
    - 数据查询：分成 `userInterfaceInfoMapper` 和 `interfaceInfoService` 层完成接口调用记录和接口详情的查询，符合分层设计思想。

2. **数据分组**：
    - 按接口 ID 对调用记录分组，便于后续聚合统计调用次数。

3. **VO 数据封装**：
    - 通过 `BeanUtils.copyProperties` 和补充字段信息，将数据转换为前端友好的 `InterfaceInfoVO` 格式。

4. **异常处理**：
    - 通过 `CollectionUtils.isEmpty` 检查数据完整性，抛出业务异常避免后续空指针错误。

---

### 可优化的地方
#### 1. **减少数据库查询次数**
目前的逻辑存在**N+1 查询问题**：
- 第一次查询 `listTopInvokeInterfaceInfo(3)` 返回调用记录。
- 第二次通过分组 ID 查询接口信息列表 `interfaceInfoService.list(queryWrapper)`。

可以优化为**一次性查询**：
- 在 `listTopInvokeInterfaceInfo(3)` 中直接使用 SQL 联表查询，获取完整的接口信息及调用次数，减少第二次查询的开销。

**优化代码示例**：
```java
// Mapper层直接联表查询返回完整数据
List<InterfaceInfoVO> listTopInvokeInterfaceInfoWithDetails(int limit);
```
这样，`listTopInvokeInterfaceInfoWithDetails` 返回的就是带有接口详情和调用次数的 VO 对象，避免了后续的分组与聚合逻辑。

---

#### 2. **改进分组与数据聚合**
`interfaceInfoIdObjMap` 和后续的调用次数 `get(0).getTotalNum()` 显得冗余。
- 可以在 SQL 查询中直接聚合调用次数 `SUM(total_num)`。

如果 SQL 查询优化后仍需分组逻辑，可直接利用 Java 8 `Collectors.toMap` 减少不必要的中间集合：
```java
Map<Long, Integer> interfaceIdToTotalNum = userInterfaceInfoList
    .stream()
    .collect(Collectors.toMap(
        UserInterfaceInfo::getInterfaceInfoId, 
        UserInterfaceInfo::getTotalNum,
        Integer::sum // 处理同 ID 数据的聚合逻辑
    ));
```

---

#### 3. **避免使用 `BeanUtils`**
`BeanUtils.copyProperties` 性能较低且可能存在不必要的字段复制问题。
- 使用构造函数或工具类（如 MapStruct）来进行属性拷贝，提高性能和可控性。

**MapStruct 示例**：
```java
@Mapper
public interface InterfaceInfoMapper {
    InterfaceInfoVO toVO(InterfaceInfo interfaceInfo, int totalNum);
}
```
然后：
```java
// 调用 Mapper 进行转换
interfaceInfoVOList = list.stream()
    .map(info -> interfaceInfoMapper.toVO(info, interfaceIdToTotalNum.get(info.getId())))
    .collect(Collectors.toList());
```

---

#### 4. **提升异常处理方式**
抛出 `BusinessException` 时建议带上更多上下文信息（如具体的接口 ID 或用户信息），方便排查问题：
```java
if (CollectionUtils.isEmpty(list)) {
    throw new BusinessException(ErrorCode.SYSTEM_ERROR, "接口信息为空，查询ID：" + interfaceInfoIdObjMap.keySet());
}
```

---

### 优化后代码示例
```java
@GetMapping("/top/interface/invoke")
@AuthCheck(mustRole = "admin")
public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterfaceInfo() {
    // 优化：直接联表查询返回完整的VO数据
    List<InterfaceInfoVO> interfaceInfoVOList = userInterfaceInfoMapper.listTopInvokeInterfaceInfoWithDetails(3);
    if (CollectionUtils.isEmpty(interfaceInfoVOList)) {
        throw new BusinessException(ErrorCode.SYSTEM_ERROR, "无调用次数前3的接口数据");
    }
    return ResultUtils.success(interfaceInfoVOList);
}
```

### 优化点总结
1. **减少数据库查询次数**：通过联表查询减少多次数据库交互。
2. **优化分组逻辑**：避免额外的分组步骤，直接通过 SQL 聚合处理。
3. **提升数据转换性能**：使用 MapStruct 替代 `BeanUtils`。
4. **丰富异常信息**：提高可读性和调试效率。