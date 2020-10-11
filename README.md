### SVN Trunks

This utility allows skipping tags and branches inside subfolders while doing SVN checkout.\
\
Built with Bazel/Graal/Scala.\
\
To build execute:\
`bazel build //:svntrunks-native`\
or\
`bazel build //:svntrunks-jar`\
\
Run binary or jar by passing checkout url, local destination, username.\
eg. `./svntrunks-native-bin http://svn.example.net/dir1/dir2/ ./ username`\
or just `./svntrunks-native-bin` and follow instructions.\
