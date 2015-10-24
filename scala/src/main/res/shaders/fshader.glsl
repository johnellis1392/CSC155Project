#version 430

in vec3 position;
in vec4 fragmentColor;
out vec4 out_color;

uniform mat4 model_view_matrix;
uniform mat4 projection_matrix;

/**
 * Simple passthrough Fragment shader 
 */
void main(void) {
    //out_color = vec4(0.0, 0.8, 1.0, 1.0);
    out_color = fragmentColor;
}
