package com.github.rockjam.models

case class Ip(origin: String)

case class UserAgent(`user-agent`: String)

case class FullInfo(origin: String, userAgent: String)
