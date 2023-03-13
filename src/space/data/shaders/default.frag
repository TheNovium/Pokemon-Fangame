#version 330 core

layout(location = 0) out vec4 color;

in DATA{
    vec4 fColor;
} fs_in;

void main() {
    color = fs_in.fColor;
}