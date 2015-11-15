#version 420 core

layout (triangles) in;
layout (triangle_strip, max_vertices = 3) out;


out float fLightMagnitude;

/*in vec4 gFragmentColor;
in vec3 gVaryingNormal;
in vec3 gVaryingLightDirection;
in vec3 gVaryingVertexPosition;

out vec4 fFragmentColor;
out vec3 fVaryingNormal;
out vec3 fVaryingLightDirection;
out vec3 fVaryingVertexPosition;*/



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


vec4 midpoint(const vec4 v1, const vec4 v2) {
	return (v2 + v1) / 2;
}


void main(void) {
	const vec4 v0 = gl_in[0].gl_Position;
	const vec4 v1 = gl_in[1].gl_Position;
	const vec4 v2 = gl_in[2].gl_Position; 
	const vec4 mid = midpoint(v0, midpoint(v1, v2));
	const vec4 normal = vec4(cross(v0.xyz, v1.xyz), 1.0);
	const vec4 lightDirection = mid - vec4(positional_light.position, 1.0);
	const float lightMagnitude = dot(lightDirection, normal);
	
	for(int i = 0; i < 3; i++) {
		gl_Position = gl_in[i].gl_Position;
		EmitVertex();
	}
	
	fLightMagnitude = lightMagnitude;
}




