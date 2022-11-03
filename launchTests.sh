#!/usr/bin/env bash
#
# Auteur : Sarah Maxel
# Date : Novembre 2022
# Version 1.0.0 : launch tests"
#


file="src/test/resources/extent.properties"
if [ -f "$file" ] ; 
then
    rm "$file"
fi
