scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

addMavenResolverPlugin
addSbtPlugin("org.skinny-framework" % "sbt-servlet-plugin" % "2.2.3")
addSbtPlugin("org.scalariform"      % "sbt-scalariform"    % "1.6.0")
addSbtPlugin("com.typesafe.sbt"     % "sbt-start-script"   % "0.10.0")
