#version 330 core

layout (location = 0) in vec4 pos;
layout (location = 1) in vec4 color;
layout (location = 2) in vec2 texCoord;
layout (location = 3) in float id;

uniform mat4 ortho_matrix;
uniform mat4 view_matrix;

out DATA {
    vec4 color;
    vec2 pos;
    float id;
} fs_out;

void main(){
    fs_out.color = color;
    fs_out.pos = texCoord;
    fs_out.id = id;

    gl_Position = ortho_matrix * view_matrix * pos;
}