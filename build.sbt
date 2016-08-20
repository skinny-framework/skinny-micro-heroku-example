lazy val root = (project in file("."))
  .settings(
    organization := "org.skinny-framework",
    name := "skinny-micro-heroku-example",
    version := "0.1",
    scalaVersion := "2.11.8",
    resolvers += "sonatype releases" at "https://oss.sonatype.org/content/repositories/releases",
    dependencyOverrides := Set("org.scala-lang" %  "scala-compiler" % scalaVersion.value), // for Scalate
    libraryDependencies ++= Seq(
      "org.skinny-framework" %% "skinny-micro"         % skinnyMicroVersion % Compile,
      "org.skinny-framework" %% "skinny-micro-server"  % skinnyMicroVersion % Compile,
      "org.skinny-framework" %% "skinny-micro-jackson" % skinnyMicroVersion % Compile,
      "org.skinny-framework" %% "skinny-micro-scalate" % skinnyMicroVersion % Compile,
      "org.eclipse.jetty"    %  "jetty-webapp"         % "9.2.18.v20160721" % "container",
      "org.skinny-framework" %% "skinny-micro-test"    % skinnyMicroVersion % Test
    ),
    mainClass in Compile := Some("skinny.standalone.JettyLauncher"),
    // add src/main/webapp to unmanaged resources for sbt-start-script
    unmanagedResourceDirectories in Compile <++= baseDirectory { base => 
      sys.env.get("LOCAL_DEV").map(_ => Seq.empty).getOrElse(Seq(base / "src/main/webapp"))
    },
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")
  )
  .settings(servletSettings)
  .settings(scalariformSettings)
  .settings(com.typesafe.sbt.SbtStartScript.startScriptForClassesSettings)

lazy val skinnyMicroVersion = "1.1.+"
