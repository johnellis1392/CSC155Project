#version 420

layout (location=0) in vec3 vertexPosition;
//layout (location=1) in vec3 vertexNormal;

/*out vec4 gFragmentColor;
out vec3 gVaryingNormal;
out vec3 gVaryingLightDirection;
out vec3 gVaryingVertexPosition;*/

out vec4 fFragmentColor;
out vec3 fVaryingNormal;
out vec3 fVaryingLightDirection;
out vec3 fVaryingVertexPosition;


// Structure for holding light data
struct PositionalLight {
  vec4 ambient;
  vec4 diffuse;
  vec4 specular;
  vec3 position;
};


// Material Structure for lighting stuff
struct Material {
  vec4 ambient;
  vec4 diffuse;
  vec4 specular;
  float shininess;
};


// Uniform Variables for World Object Transforms
uniform mat4 view_matrix;
uniform mat4 projection_matrix;
uniform mat4 model_matrix;
uniform mat4 normal_matrix;


// Uniforms for lighting calculations
uniform vec4 global_ambient;
uniform PositionalLight positional_light;
uniform Material material;



/**
 * Calculate MVP Matrix
 */
vec4 mvp_matrix(const mat4 p, const mat4 m, const mat4 v, const vec3 pos) {
	return p * m * v * vec4(pos, 1.0);
}

mat4 getNormalMatrix(const mat4 m, const mat4 v) {
	return transpose(inverse(m * v));
}

void main(void) {
	const vec4 position = mvp_matrix(projection_matrix, model_matrix, view_matrix, vertexPosition);
	const mat4 normalMatrix = getNormalMatrix(model_matrix, view_matrix);
	const vec3 lightDirection = vec3(
		vertexPosition.x - positional_light.position.x,
		vertexPosition.y - positional_light.position.y,
		vertexPosition.z - positional_light.position.z
	);
	
	const vec4 vertexNormal = vec4(cross(lightDirection, vec3(0.0,0.0,0.0)), 1.0);
	
	fVaryingVertexPosition = (position * vec4(vertexPosition, 1.0)).xyz;
	fVaryingLightDirection = positional_light.position - fVaryingVertexPosition;
	fVaryingNormal = (normal_matrix * vertexNormal).xyz; 
	 
    gl_Position = position;
    fFragmentColor = vec4(vertexPosition, 1.0) * 2 + vec4(0.5, 0.5, 0.5, 0.0);
}













