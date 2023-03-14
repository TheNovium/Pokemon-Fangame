#version 330 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 tc;

uniform mat4 ortho_matrix;
uniform mat4 view_matrix;

out DATA {
    vec2 tc;
} fs_out;

void main(){
    gl_Position = ortho_matrix * view_matrix * position;
    fs_out.tc = tc;
}