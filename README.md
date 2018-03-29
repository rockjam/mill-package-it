## Package your app with mill

This is simple example of packaging application in [sbt native packager](https://github.com/sbt/sbt-native-packager) style. 
You need to run `mill root.packageIt` to make an assembly. Results will be in `out/root/packageIt/dest` directory.

### Quick start

1. Execute `./package.sh` to package application. This will produce distributable file `dist.tar.gz`, that you can untar and run with `bin/run`

2. To untar and run execute `./run.sh`. It will untar distributable and run application.

3. Check that it works with `curl -XGET localhost:9000/about-me`
