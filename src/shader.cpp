#include "shader.hpp"

Shader::Shader( const std::string &vsPath, const std::string &fsPath ) 
{
    Load( vsPath, fsPath );
}

void Shader::Load( const std::string &vsPath, const std::string &fsPath ) 
{
    const std::string vShaderCode = LoadFile(vsPath);
    const std::string fShaderCode = LoadFile(fsPath);

    GLint success;
    GLchar infoLog[512];

    GLuint shaderId = Compile( vShaderCode.c_str(), GL_VERTEX_SHADER, &success, infoLog );
    GLuint fragmentId = Compile( fShaderCode.c_str(), GL_FRAGMENT_SHADER, &success, infoLog );
    
    programId = Link( shaderId, fragmentId, &success, infoLog );
    
    glDeleteShader(shaderId);
    glDeleteShader(fragmentId);
}

const std::string Shader::LoadFile( const std::string &path ) 
{
    std::string content;
    std::ifstream file;
    // ensure ifstream objects can throw exceptions:
    file.exceptions (std::ifstream::failbit | std::ifstream::badbit);
    try 
    {
        // open files
        file.open(path);
        std::stringstream shaderStream;
        // read file's buffer contents into streams
        shaderStream << file.rdbuf();
        // close file handlers
        file.close();
        // convert stream into string
        content = shaderStream.str();
    }
    catch (std::ifstream::failure& e)
    {
        std::cout << "ERROR::SHADER::FILE_NOT_SUCCESFULLY_READ: " << e.what() << std::endl;
    }
    return content;
}

GLuint Shader::Compile( const GLchar* shaderSource, GLint shaderType, int* success, GLchar infoLog[512] ) 
{
    // build and compile our shader program
    // ------------------------------------
    GLuint shader = glCreateShader(shaderType);
    glShaderSource(shader, 1, &shaderSource, NULL);
    glCompileShader(shader);

    // check for shader compile errors
    glGetShaderiv(shader, GL_COMPILE_STATUS, success);
    if (!success)
    {
        glGetShaderInfoLog(shader, 512, NULL, infoLog);
        std::cout << "ERROR::SHADER::COMPILATION_FAILED\n" << infoLog << std::endl;
    }
    return shader;
}

GLuint Shader::Link( GLint vsId, GLint fsId, int* success, GLchar infoLog[512] ) 
{
    GLuint shaderProgramId = glCreateProgram();
    glAttachShader(shaderProgramId, vsId);
    glAttachShader(shaderProgramId, fsId);
    glLinkProgram(shaderProgramId);

    glGetProgramiv(shaderProgramId, GL_LINK_STATUS, success);
    if (!success) {
        glGetProgramInfoLog(shaderProgramId, 512, NULL, infoLog);
        std::cout << "ERROR::SHADER::PROGRAM::LINKING_FAILED\n" << infoLog << std::endl;
    }
    return shaderProgramId;
}

void Shader::Activate() 
{
    glUseProgram(programId);
}

Shader::~Shader() 
{
    glDeleteProgram( programId );
}
