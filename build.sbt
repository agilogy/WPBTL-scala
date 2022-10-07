import Dependencies._

ThisBuild / scalaVersion := "2.13.9"
ThisBuild / version := "0.1.0"
ThisBuild / organization := "com.agilogy"
ThisBuild / organizationName := "agilogy"

lazy val root = (project in file("."))
  .settings(
    name := "wpbtl-scala",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += scalaTestFunSuite % Test,
  )