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



vec4 getLighting(const mat4 mv_matrix, 
				  const vec3[3] vertices,
				  const PositionalLight positional_light,
				  const Material material,
				  const vec3 global_ambient) {
	
	const mat4 normal_matrix = getNormalMatrix(mv_matrix);
	vec4 center = getCenter(vertices);
	vec4 P = mv_matrix * center;
	vec3 N = normalize((normal_matrix * normal).xyz);
	vec3 L = normalize(positional_light.position - P.xyz);
	vec3 V = normalize(-P.xyz);
	vec3 R = reflect(-L, N);
	
	const vec3 ambient = 
		(global_ambient * material.ambient + 
		 positional_light.ambient * material.ambient).xyz;
	const vec3 diffuse = 
		positional_light.diffuse.xyz * 
		material.diffuse.xyz * 
		max(dot(N, L), 0.0);
	const vec3 specular = 
		pow(max(dot(R, V), 0.0f), material.shininess) * 
		material.specular.xyz * 
		positional_light.specular.xyz;
	
	return vec4((ambient + diffuse + specular), 1.0);
}



void main(void) {
	vec3[] vertices[gl_in.length];
	for(int i = 0; i < gl_in.length; i++) {
		vertices[i] = gl_in[i].gl_Position;
	}
	
	emitVertices(vertices);
	const vec4 lightMagnitude = getLighting(
		mv_matrix,
		vertices,
		positional_light,
		material,
		global_ambient
	);
	
	fLightMagnitude = lightMagnitude;
	
	for(int i = 0; i < vertices.length; i++) {
		gl_Position = vertices[i];
		EmitVertex();
	}
}





















