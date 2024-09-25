package com.bo.roomdemo.data

data class IrRemoteInfo (
  val remoteId: Long,
  val remoteName: String,
  val remoteModel: String,
  val remoteNote: String,
  val remoteIsActive: Boolean,
  val remoteTheme: Long,
  val remoteAccessDateTime: String,
  val remoteButtons: List<RemoteButton>
)