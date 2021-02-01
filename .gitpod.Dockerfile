FROM gitpod/workspace-full-vnc
                    
USER ROOT

RUN apt-get update 
RUN apt-get -y install libncurses5 libncurses5:i386


USER gitpod
