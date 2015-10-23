#version 420

in vec3 position;
out vec4 fragmentColor;

uniform mat4 model_view_matrix;
uniform mat4 projection_matrix;

/**
 * Simple passthrough Fragment shader 
 */
void main(void) {
    fragmentColor = vec4(0.0, 0.8, 1.0, 1.0);
}
