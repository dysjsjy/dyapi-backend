package com.dysjsjy.clientsdk.model;

public class RandomWallpaperResponse extends ResultResponse {

    private static final long serialVersionUID = 1L;

    private String imgUrl;

    public RandomWallpaperResponse() {
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "RandomWallpaperResponse(imgUrl=" + this.getImgUrl() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof RandomWallpaperResponse)) {
            return false;
        } else {
            RandomWallpaperResponse other = (RandomWallpaperResponse)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (!super.equals(o)) {
                return false;
            } else {
                Object this$imgurl = this.getImgUrl();
                Object other$imgurl = other.getImgUrl();
                if (this$imgurl == null) {
                    if (other$imgurl != null) {
                        return false;
                    }
                } else if (!this$imgurl.equals(other$imgurl)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RandomWallpaperResponse;
    }

    public int hashCode() {
        int result = super.hashCode();
        Object $imgurl = this.getImgUrl();
        result = result * 59 + ($imgurl == null ? 43 : $imgurl.hashCode());
        return result;
    }
}
