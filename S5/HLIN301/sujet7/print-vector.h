#ifndef __PRINT_VECTOR_H
#define __PRINT_VECTOR_H
#include "cellule.h"
#include "myvector.h"
template<typename Vector>	
void read(std::istream& is, Vector& T){  
  for(size_t i=0;i<T.size();i++)
     {is>>T.at(i);}  	     
}
 
template<typename E>
void write(std::ostream& os, const MyVector<E>& T){
  os<<"code generique: ";
  for(size_t i=0;i<T.size();i++)
  {os<<T.at(i)<<(i!=T.size()-1?",":"");}
  os<<std::endl;				
} 	     				     

template<typename Vect>
void write(std::ostream& os, const Vect& T){
  os<<"code generique: ";
  for(size_t i=0;i<T.size();i++)
  {os<<T.at(i)<<(i!=T.size()-1?",":"");}
  os<<std::endl;
} 	     				     

template<>
void write<Cellule>(std::ostream& os, const MyVector<Cellule>& T){
  os<<"code specifgique: ";
  for(size_t i=0;i<T.size();i++)
    {write_cell(os,T.at(i));
      os<<(i!=T.size()-1?",":"");}
  os<<std::endl;
} 	     				     


#endif
