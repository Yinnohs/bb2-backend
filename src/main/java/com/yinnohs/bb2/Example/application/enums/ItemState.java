package com.yinnohs.bb2.Example.application.enums;

public enum ItemState {
    Active("ACTIVE"),
    Discontinued("DISCONTINUED");

    String value;

    ItemState(String value) {
        this.value = value;
    }

    String getValue (ItemState itemState){
       return this.value;
    }

    public static ItemState getFromString(String state){
        if(state == null)
            return null;
        for(ItemState itemStateStr : values()) {
            if(itemStateStr.value.equalsIgnoreCase(state))
                return itemStateStr;
        }
        return null;
    }


}
