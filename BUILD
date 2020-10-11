load("@io_bazel_rules_scala//scala:scala.bzl", "scala_binary", "scala_library")
load("@rules_graal//graal:graal.bzl", "graal_binary")

scala_library(
    name = "svntrunks",
    srcs = glob(["src/main/scala/dule12/**/*.scala"]),
    deps = [        
        "@maven//:org_tmatesoft_svnkit_svnkit"
    ]
)

graal_binary(
    name = "svntrunks-native",
    deps = [":svntrunks"],
    main_class = "dule12.Main",
    graal_extra_args = [
        "--report-unsupported-elements-at-runtime"
    ]
)

scala_binary(
    name = "svntrunks-jar",
    deps = [":svntrunks"],
    main_class = "dule12.Main"
)