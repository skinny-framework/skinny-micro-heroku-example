import skinny.servlet._, ServletPlugin._, ServletKeys._
lazy val jettyVersion = "9.4.9.v20180320"

lazy val root = (project in file("."))
  .settings(
    organization := "org.skinny-framework",
    name := "skinny-micro-heroku-example",
    version := "0.1",
    scalaVersion := "2.12.4",
    resolvers += "sonatype releases" at "https://oss.sonatype.org/content/repositories/releases",
    dependencyOverrides := Set("org.scala-lang" %  "scala-compiler" % scalaVersion.value), // for Scalate
    libraryDependencies ++= Seq(
      "org.skinny-framework" %% "skinny-micro"         % skinnyMicroVersion % Compile,
      "org.skinny-framework" %% "skinny-micro-server"  % skinnyMicroVersion % Compile,
      "org.skinny-framework" %% "skinny-micro-jackson" % skinnyMicroVersion % Compile,
      "org.skinny-framework" %% "skinny-micro-scalate" % skinnyMicroVersion % Compile,
      "org.eclipse.jetty"    %  "jetty-webapp"         % jettyVersion       % "container",
      "org.eclipse.jetty"    %  "jetty-plus"           % jettyVersion       % "container",
      "org.skinny-framework" %% "skinny-micro-test"    % skinnyMicroVersion % Test
    ),
    mainClass in Compile := Some("skinny.standalone.JettyLauncher"),
    // add src/main/webapp to unmanaged resources for sbt-start-script
    unmanagedResourceDirectories in Compile ++= {
      val base = baseDirectory.value
      sys.env.get("LOCAL_DEV").map(_ => Seq.empty).getOrElse(Seq(base / "src/main/webapp"))
    },
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
  )
  .settings(servletSettings)
  .settings(com.typesafe.sbt.SbtStartScript.startScriptForClassesSettings)

lazy val skinnyMicroVersion = "1.3.+"
