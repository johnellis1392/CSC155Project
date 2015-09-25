#version 210
//#version 430

in vec4 varyingColor;

out vec4 fragmentColor; 

/**
 * Simple passthrough Fragment shader 
 */
void main(void) {
  fragmentColor = varyingColor; 
}

