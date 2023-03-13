#version 330 core

in DATA{
    vec4 fColor;
} fs_in;

void main() {
    gl_FragColor = fs_in.fColor;
}