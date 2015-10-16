//#version 210
#version 430

//in vec3 vertexPosition;
//in vec4 vertexColor;

//out vec4 varyingColor;

//layout (location=0) in vec3 position;
//
//uniform mat4 model_view_matrix;
//uniform mat4 projection_matrix;

/**
 * Simple passthrough shader for now 
 */
void main(void) {
//    varyingColor = vertexColor;
//    gl_Position = vec4(vertexPosition, 1);
//    gl_Position = vec4(0.0, 0.0, 0.5, 1.0);

    const vec4 vertices[3] = vec4[3] (
        vec4(0.25, -0.25, 0.5, 1.0),
        vec4(-0.25, -0.25, -.5, 1.0),
        vec4(0.25, 0.25, -0.5, 1.0)
    );

    gl_Position = vertices[gl_VertexID];
//    gl_Position = vec4(0.0, 0.0, 0.5, 1.0);

//    gl_Position = projection_matrix *
//        model_view_matrix *
//        vec4(position, 1.0);
}

