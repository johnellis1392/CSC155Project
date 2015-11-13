

java_import com.jogamp.opengl.GL4
#java_import graphicslib3D.Matrix3D
#$CLASSPATH << 'lib'
#require 'graphicslib3D.*'
#java_import graphicslib3D

#$gl.glBindBuffer(GL4::GL_ARRAY_BUFFER, $VBO[0]);
#$gl.glVertexAttribPointer(0, 3, GL4::GL_FLOAT, false, 0, 0);
#$gl.glEnableVertexAttribArray(0);
#
#$gl.glEnable(GL4::GL_CULL_FACE);
#$gl.glFrontFace(GL4::GL_CW);
#$gl.glEnable(GL4::GL_DEPTH_TEST);
#$gl.glDepthFunc(GL4::GL_LEQUAL);
#
#$gl.glDrawArrays(GL4::GL_TRIANGLES, 0, 36);


#$GameState.getCamera.translate(-0.001, 0.0, 0.0)
#$GameState.getCamera.rotate(2.0, 0.0, 0.0)
#$GameState.getCamera.translate(0.0, 0.0, Math.sin(Time.now.to_f * 5.0))
