#version 330 core

in DATA {
    vec4 color;
    vec2 pos;
    float id;
} fs_in;

uniform sampler2D textures[8];

out vec4 fcolor;

void main(){
    int id = int(fs_in.id);
    if(id == 0){
        fcolor = fs_in.color;
    } else {
        fcolor = fs_in.color * texture(textures[id], fs_in.pos);
    }
}