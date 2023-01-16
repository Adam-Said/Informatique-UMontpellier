// --------------------------------------------------------------------------
// gMini,
// a minimal Glut/OpenGL app to extend                              
//
// Copyright(C) 2007-2009                
// Tamy Boubekeur
//                                                                            
// All rights reserved.                                                       
//                                                                            
// This program is free software; you can redistribute it and/or modify       
// it under the terms of the GNU General Public License as published by       
// the Free Software Foundation; either version 2 of the License, or          
// (at your option) any later version.                                        
//                                                                            
// This program is distributed in the hope that it will be useful,            
// but WITHOUT ANY WARRANTY; without even the implied warranty of             
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              
// GNU General Public License (http://www.gnu.org/licenses/gpl.txt)           
// for more details.                                                          
//                                                                          
// --------------------------------------------------------------------------
   
#pragma once

#include <iostream>
#include <vector>

#include "Vec3D.h"

class Vertex {
public:
    inline Vertex () 
        : position (Vec3 (0.0,0.0,0.0)), normal (Vec3 (0.0, 0.0, 1.0)) {}
    inline Vertex (const Vec3 & pos)
        : position (pos), normal (Vec3 (0.0, 0.0, 1.0)) {}
    inline Vertex (const Vec3 & pos, const Vec3 & normal) : position (pos), normal (normal) {}
    inline Vertex (const Vertex & v) : position (v.position), normal (v.normal) {}
    inline virtual ~Vertex () {}
    inline Vertex & operator= (const Vertex & vertex) {
        position = vertex.position;
        normal = vertex.normal;
        return (*this);
    }
    inline const Vec3 & getPosition () const { return position; }
    inline const Vec3 & getNormal () const { return normal; }
    inline void setPosition (const Vec3 & newPos) { position = newPos; }
    inline void setNormal (const Vec3 & newNormal) { normal = newNormal; }
    inline bool operator== (const Vertex & v) { return (v.position == position && v.normal == normal); }
    void interpolate (const Vertex & u, const Vertex & v, float alpha = 0.5);

    static void computeAveragePosAndRadius (std::vector<Vertex> & vertices, 
                                            Vec3 & center, float & radius);
    static void scaleToUnitBox (std::vector<Vertex> & vertices, 
                                Vec3 & center, float & scaleToUnitBox);
    static void normalizeNormals (std::vector<Vertex> & vertices);

    Vec3 position;
    Vec3 normal;
};

extern std::ostream & operator<< (std::ostream & output, const Vertex & v);

// Some Emacs-Hints -- please don't remove:
//
//  Local Variables:
//  mode:C++
//  tab-width:4
//  End:
