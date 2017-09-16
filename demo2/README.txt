this  is  a  demo   test maven plugin

like  maven-assembly-plugin     maven-dependency-plugin

assembly  is  use  for package with   the  command  -P   dev

help us  build  the  package like  uat prod for  diff properties

dependency

this is  use  for del the dependency  plugin we use we can make it in our package

dependency:
    copy takes a list of artifacts defined in the plugin configuration section and copies them to a specified
location, renaming them or stripping the version if desired. This goal can resolve the artifacts from remote repositories
if they don't exist in either the local repository or the reactor.
copy 可以用于解决远程jar包不存在 ，可以在package阶段将其放入一个特殊的位置
http://maven.apache.org/components/plugins/maven-dependency-plugin/copy-mojo.html



dependency:
    unpack like copy but unpacks.
    http://maven.apache.org/components/plugins/maven-dependency-plugin/unpack-mojo.html


command:

    cd  demo2
    mvn clean package -P dev