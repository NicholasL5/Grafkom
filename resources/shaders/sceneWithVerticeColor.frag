#version 330
// out = variable disini dilempar keluar
// in = meminta variabel dari .vert
//uniform vec4 uni_color;
out vec4 frag_color;
in vec4 out_color;
void main() {
    //frag_color = vec4(1.0, 0.0, 0.0, 0.0);
    frag_color = out_color;
}