#version 330 core

layout (location = 0) out vec4 color;

uniform sampler2D tex;
uniform float alpha;

in DATA {
    vec2 tc;
} fs_in;

void main(){
    color = texture(tex, fs_in.tc);
    color.a *= alpha;
}