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
   
#include "Vertex.h"

#include <cmath>
#include <algorithm>

#include "Vec3D.h"

using namespace std;

static const unsigned int SIZE_OF_VERTEX = 10;

ostream & operator<< (ostream & output, const Vertex & v) {
    output << v.getPosition () << endl
           << v.getNormal () << endl;
    return output;
}

void Vertex::interpolate (const Vertex & u, const Vertex & v, float alpha) {
    setPosition (Vec3::interpolate (u.getPosition (), v.getPosition (), alpha));
    Vec3 normal = Vec3::interpolate (u.getNormal (), v.getNormal (), alpha);
    normal.normalize ();
    setNormal (normal);
}

// ------------------------------------
// Static Members Methods.
// ------------------------------------

void Vertex::computeAveragePosAndRadius (std::vector<Vertex> & vertices, 
                                         Vec3 & center, float & radius) {
    center = Vec3 (0.0, 0.0, 0.0);
    for (unsigned int i = 0; i < vertices.size (); i++)
        center += vertices[i].getPosition ();
    center /= float (vertices.size ());
    radius = 0.0;
    for (unsigned int i = 0; i < vertices.size (); i++) {
        float vDistance = Vec3::distance (center, vertices[i].getPosition ());
        if (vDistance > radius)
            radius = vDistance;
    }
}

void Vertex::scaleToUnitBox (vector<Vertex> & vertices, 
                             Vec3 & center, float & scaleToUnit) {
    computeAveragePosAndRadius (vertices, center, scaleToUnit);
    for (unsigned int i = 0; i < vertices.size (); i++) 
        vertices[i].setPosition (Vec3::segment (center, vertices[i].getPosition ()) / scaleToUnit);
}

void Vertex::normalizeNormals (vector<Vertex> & vertices) {
    for (std::vector<Vertex>::iterator it = vertices.begin (); 
         it != vertices.end (); 
         it++) {
        Vec3 n = it->getNormal ();
        if (n != Vec3 (0.0, 0.0, 0.0)) {
            n.normalize ();
            it->setNormal (n);
        }
    }    
}

