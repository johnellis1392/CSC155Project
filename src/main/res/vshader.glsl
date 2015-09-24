#version 430

in vec3 vertexPosition;
in vec4 vertexColor;

out vec4 varyingColor; 

/**
 * Simple passthrough shader for now 
 */
void main(void) {
  varyingColor = vertexColor;
  gl_Position = vec4(vertexPosition, 1); 
}

