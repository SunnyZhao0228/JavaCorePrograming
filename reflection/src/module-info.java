module reflection {
    // requires 内部依赖
    // 命名模块
    // artifact 资源根 package 存有 module-info.class（包含模块名称）
    requires java.base; // 默认依赖
    requires java.sql;  // 传递依赖 requires transitive
    requires java.compiler;  // exports 控制可访问的 API 包
    requires java.desktop;
    requires org.apache.commons.collections4;
    requires cglib;

    exports reflection.action.example1;
    exports reflection.action.example2;
    exports reflection.aopdemo;
    exports reflection.constructor;

}