#version 420

layout (location=0) in vec3 vertexPosition;

// Uniform Variables for World Object Transforms
uniform mat4 model_view_matrix;
uniform mat4 projection_matrix;


/**
 * Simple passthrough shader for now 
 */
void main(void) {
    gl_Position = projection_matrix * model_view_matrix * vec4(vertexPosition, 1.0);
}


/**
 * Calculate MVP Matrix
 */
vec4 mvp_matrix(const mat4 proj, const mat4 mv_matrix, const vec3 pos) {
	return proj * mv_matrix * vec4(pos, 1.0);
}

