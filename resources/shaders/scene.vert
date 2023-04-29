#version 330
layout(location = 0) in vec3 position;
uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;
void main() {
    // gl_Position tidak boleh diganti
    gl_Position = model * (vec4(position, 1.0) );
    gl_Position = projection * view * model * vec4(position, 1.0);
}