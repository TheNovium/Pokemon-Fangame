#version 330 core
layout (location = 0) in vec3 pos;
layout (location = 1) in vec4 color;

out vec4 fColor;

void main() {
    fColor = color;
    gl_position = vec4(pos, 1.0);
}