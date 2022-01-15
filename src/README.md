# OpenGL Learning

## Vertex Buffer Object (VBO)

Vertex Buffer Objects are objects that store an array of unformatted data in the GPU's memory. They are primarily used to store vertex data and allow large batches of data to be sent to the GPU at once. Once the VBO is stored in the GPU it has very fast read access to it. 

Each VBO has a unique ID and can be generated like so:
```
unsigned int VBO;
glGenBuffers(1, &VBO);
```

The state machine only allows a single VBO to be bound at a given time.

## Vertex Array Object (VAO)

