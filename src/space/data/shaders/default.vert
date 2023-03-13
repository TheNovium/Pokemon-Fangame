#version 330 core
layout (location = 0) in vec3 pos;
layout (location = 1) in vec4 color;

out DATA{
    vec4 fColor;
} fs_out;

void main() {
    fs_out.fColor = color;
    gl_Position = vec4(pos, 1.0);
}