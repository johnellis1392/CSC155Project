#version 210
//#version 430

in vec3 position;
in vec4 varyingColor;
out vec4 fragmentColor;

uniform mat4 model_view_matrix;
uniform mat4 projection_matrix;

/**
 * Simple passthrough Fragment shader 
 */
void main(void) {
//    fragmentColor = varyingColor;
    fragmentColor = vec4(1.0, 0.0, 0.0, 1.0);
}

