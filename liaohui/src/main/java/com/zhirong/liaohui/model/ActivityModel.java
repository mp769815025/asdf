package com.zhirong.liaohui.model;

import com.zhirong.liaohui.entity.Activity;
import com.zhirong.liaohui.entity.UserActivitySelection;
import lombok.Data;

@Data
public class ActivityModel {

    private Activity activity;

    private UserActivitySelection userActivitySelection;
}
