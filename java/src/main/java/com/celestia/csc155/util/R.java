
package com.celestia.csc155.util;

public class R {
    public static final String res = "src/main/res/";
//    public static final String res = "./res/";

    public static class shaders {
        public static final String shaders = res + "shaders/";
        public static final String fshader = shaders + "fshader.glsl";
        public static final String vshader = shaders + "vshader.glsl";
        public static final String gshader = shaders + "gshader.glsl";
        public static final String tcshader = shaders + "tcshader.glsl";
        public static final String teshader = shaders + "teshader.glsl";
    }

    public static class util {
        public static final String title = "John Ellis - A1";
        public static final int fps = 50;
        public static final double aspect = 16.0 / 9.0;
        public static final int width = 1000;
        public static final int height = (int) (width / aspect);
    }

    public static class ruby {
        public static final String ruby = res + "ruby/";
        public static final String main = ruby + "main.rb";
        
        public static class callbacks {
        	public static final String onInit = "onInit";
        	public static final String onUpdate = "onUpdate";
        	public static final String onDisplay = "onDisplay";
        	public static final String onDispose = "onDispose";
        }
    }

    public static class uniforms {
        public static final String model_view_matrix = "model_view_matrix";
        public static final String projection_matrix = "projection_matrix";
    }

    public static class vertices {
        public static final float[] pyramid = new float[] {
                0.25f, -0.25f, -0.25f, -0.25f, -0.25f, -0.25f, 0.0f, 0.25f, 0.0f,
                -0.25f, -0.25f, -0.25f, -0.25f, -0.25f, 0.25f, 0.0f, 0.25f, 0.0f,
                -0.25f, -0.25f, 0.25f, 0.25f, -0.25f, 0.25f, 0.0f, 0.25f, 0.0f,
                0.25f, -0.25f, 0.25f, 0.25f, -0.25f, -0.25f, 0.0f, 0.25f, 0.0f,
                -0.25f, -0.25f, -0.25f, 0.25f, -0.25f, 0.25f, -0.25f, -0.25f, 0.25f,
                -0.25f, -0.25f, -0.25f, 0.25f, -0.25f, -0.25f, 0.25f, -0.25f, 0.25f
        };
        
        public static final float[] cube = new float [] {
        	-0.25f,  0.25f, -0.25f, -0.25f, -0.25f, -0.25f, 0.25f, -0.25f, -0.25f,
         	0.25f, -0.25f, -0.25f, 0.25f,  0.25f, -0.25f, -0.25f,  0.25f, -0.25f,
         	0.25f, -0.25f, -0.25f, 0.25f, -0.25f,  0.25f, 0.25f,  0.25f, -0.25f,
         	0.25f, -0.25f,  0.25f, 0.25f,  0.25f,  0.25f, 0.25f,  0.25f, -0.25f,
         	0.25f, -0.25f,  0.25f, -0.25f, -0.25f,  0.25f, 0.25f,  0.25f,  0.25f,
        	-0.25f, -0.25f,  0.25f, -0.25f,  0.25f,  0.25f, 0.25f,  0.25f,  0.25f,
        	-0.25f, -0.25f,  0.25f, -0.25f, -0.25f, -0.25f, -0.25f,  0.25f,  0.25f,
        	-0.25f, -0.25f, -0.25f, -0.25f,  0.25f, -0.25f, -0.25f,  0.25f,  0.25f,
        	-0.25f, -0.25f,  0.25f,  0.25f, -0.25f,  0.25f,  0.25f, -0.25f, -0.25f,
         	0.25f, -0.25f, -0.25f, -0.25f, -0.25f, -0.25f, -0.25f, -0.25f,  0.25f,
         	-0.25f,  0.25f, -0.25f, 0.25f,  0.25f, -0.25f, 0.25f,  0.25f,  0.25f,
         	0.25f,  0.25f,  0.25f, -0.25f,  0.25f,  0.25f, -0.25f,  0.25f, -0.25f
        };
    }
}