public class Base extends groovy.lang.GroovyObjectSupport implements groovy.lang.GroovyObject {
public Base(java.lang.Integer a) {
}
}
public class Inheritor extends Base implements groovy.lang.GroovyObject {
public Inheritor(int x, int y) {
super(x);
org.codehaus.groovy.runtime.DefaultGroovyMethods.print(this, y);
}
}
