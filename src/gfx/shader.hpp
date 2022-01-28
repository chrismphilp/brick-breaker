#pragma once

#include <glad/glad.h>
#include <string>
#include <fstream>
#include <sstream>
#include <iostream>

class Shader 
{
public:
    GLuint programId;

    Shader( const std::string &vsPath, const std::string &fsPath );

    void Activate();

    ~Shader();

private:
    void Load( const std::string &vsPath, const std::string &fsPath );
    const std::string LoadFile( const std::string &path );
    GLuint Compile( const GLchar* shaderSource, GLint shaderType, int* success, GLchar infoLog[512] );
    GLuint Link( GLint vsId, GLint fsId, int* success, GLchar infoLog[512] );
};