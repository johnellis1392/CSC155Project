#version 430

layout (location=0) in vec3 vertexPosition;
//layout (location=1) in vec4 vertexColor;
out vec4 fragmentColor;

// Uniform Variables for World Object Transforms
uniform mat4 view_matrix;
uniform mat4 projection_matrix;
uniform mat4 model_matrix;


/**
 * Simple passthrough shader for now 
 */
void main(void) {
    //gl_Position = projection_matrix * model_view_matrix * vec4(vertexPosition, 1.0);
    gl_Position = projection_matrix * view_matrix * model_matrix * vec4(vertexPosition, 1.0);
    fragmentColor = vec4(vertexPosition, 1.0) * 2 + vec4(0.5, 0.5, 0.5, 0.0);
    //fragmentColor = vertexColor;
}


/**
 * Calculate MVP Matrix
 */
vec4 mvp_matrix(const mat4 proj, const mat4 mv_matrix, const vec3 pos) {
	return proj * mv_matrix * vec4(pos, 1.0);
}

