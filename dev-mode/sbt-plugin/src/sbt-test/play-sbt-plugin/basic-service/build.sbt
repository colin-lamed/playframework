//
// Copyright (C) Lightbend Inc. <https://www.lightbend.com>
//

lazy val root = (project in file("."))
  .enablePlugins(PlayService)
  .settings(
    scalaVersion := ScriptedTools.scalaVersionFromJavaProperties(),
    updateOptions := updateOptions.value.withLatestSnapshots(false),
    update / evictionWarningOptions ~= (_.withWarnTransitiveEvictions(false).withWarnDirectEvictions(false)),
    PlayKeys.playInteractionMode := play.sbt.StaticPlayNonBlockingInteractionMode,
    libraryDependencies += guice,
    InputKey[Unit]("verifyResourceContains") := {
      val args       = Def.spaceDelimited("<path> <status> <words> ...").parsed
      val path       = args.head
      val status     = args.tail.head.toInt
      val assertions = args.tail.tail
      ScriptedTools.verifyResourceContains(path, status, assertions)
    }
  )
