package pojo;

public class HotelCity {
    /**
     * City ID
     */
    private String cityId;
    /**
     * City name
     */
    private String cityName;
    /**
     * City initial
     */
    private String headPinyin;
    /**
     * City pinyin
     */
    private String pinyin;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getHeadPinyin() {
        return headPinyin;
    }

    public void setHeadPinyin(String headPinyin) {
        this.headPinyin = headPinyin;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public String toString() {
        return "HotelCity [cityId=" + cityId + ", cityName=" + cityName
                + ", headPinyin=" + headPinyin + ", pinyin=" + pinyin + "]";
    }



}
