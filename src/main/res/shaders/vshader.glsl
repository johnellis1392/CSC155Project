#version 420

layout (location=0) in vec3 vertexPosition;

// Uniform Variables for World Object Transforms
uniform mat4 model_view_matrix;
uniform mat4 projection_matrix;


/**
 * Simple passthrough shader for now 
 */
void main(void) {
    //const vec4 vertices[3] = vec4[3] (
    //    vec4(0.25, -0.25, 0.5, 1.0),
    //    vec4(-0.25, -0.25, -.5, 1.0),
    //    vec4(0.25, 0.25, -0.5, 1.0)
    //);

    //gl_Position = vertices[gl_VertexID];
    //gl_Position = mvp_matrix(projection_matrix, model_view_matrix, vertexPosition);
    gl_Position = projection_matrix * model_view_matrix * vec4(vertexPosition, 1.0);
}


/**
 * Calculate MVP Matrix
 */
vec4 mvp_matrix(const mat4 proj, const mat4 mv_matrix, const vec3 pos) {
	return proj * mv_matrix * vec4(pos, 1.0);
}

