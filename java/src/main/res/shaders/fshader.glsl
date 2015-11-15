#version 420

in float fLightMagnitude;
in vec4 fFragmentColor;
in vec3 fVaryingNormal;
in vec3 fVaryingLightDirection;
in vec3 fVaryingVertexPosition;

out vec4 out_color;


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



void main(void) {
	const vec3 L = normalize(fVaryingLightDirection);
	const vec3 N = normalize(fVaryingNormal);
	const vec3 V = normalize(-fVaryingVertexPosition);
	const vec3 R = normalize(reflect(-L, N));
	
	const float cosTheta = dot(L, N);
	const float cosPhi = dot(V, R); 
	
	out_color = (global_ambient * material.ambient
		+ positional_light.ambient * material.ambient
		+ positional_light.diffuse * material.diffuse * max(cosTheta, 0.0)
		+ positional_light.specular * material.specular) * fLightMagnitude;
		//* pow(max(cosPhi, 0.0), material.shininess);
	
    //out_color = fragmentColor;
}




