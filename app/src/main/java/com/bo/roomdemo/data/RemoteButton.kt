package com.bo.roomdemo.data


data class RemoteButton (
  val remoteId: Long,
  val buttonTitle: String,
  val buttonValue: String,
  val buttonColor: String,
  val buttonTextColor: String,
  val buttonShep: String,
  val buttonSymbol: String,
  val buttonIsShowSymbol: Boolean,
  val buttonIsShowText: Boolean,
  val buttonIsEnable: Boolean,
)