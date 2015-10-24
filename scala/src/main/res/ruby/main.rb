

java_import com.jogamp.opengl.GL4

$gl.glBindBuffer(GL4::GL_ARRAY_BUFFER, $VBO[0]);
$gl.glVertexAttribPointer(0, 3, GL4::GL_FLOAT, false, 0, 0);
$gl.glEnableVertexAttribArray(0);

$gl.glEnable(GL4::GL_CULL_FACE);
$gl.glFrontFace(GL4::GL_CW);
$gl.glEnable(GL4::GL_DEPTH_TEST);
$gl.glDepthFunc(GL4::GL_LEQUAL);

$gl.glDrawArrays(GL4::GL_TRIANGLES, 0, 36);


