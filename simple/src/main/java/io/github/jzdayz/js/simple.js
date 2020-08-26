load("nashorn:mozilla_compat.js");
importPackage('io.github.jzdayz.js');


function main(map) {
    let show = Demo.show(111);
    print("java invoke res -> " + show)
    return map['res']
}