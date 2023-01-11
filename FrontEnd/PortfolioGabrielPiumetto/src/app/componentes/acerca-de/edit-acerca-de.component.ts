import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Persona } from 'src/app/model/persona.model';
import { ImageService } from 'src/app/service/image.service';
import { PersonaService } from 'src/app/service/persona.service';

@Component({
  selector: 'app-edit-acerca-de',
  templateUrl: './edit-acerca-de.component.html',
  styleUrls: ['./edit-acerca-de.component.css']
})
export class EditAcercaDeComponent implements OnInit {
   persona : Persona = null;
   
  constructor(private activatedRoute: ActivatedRoute, private personaService: PersonaService, private router: Router, public imageService : ImageService) { }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params['id'];
    this.personaService.detail(id).subscribe(
      data =>{
        this.persona = data;
      }, err =>{alert("Error al modificar Formación académica");
      this.router.navigate(['']);
    }
    )
  }

  onUpdate(): void{
    const id = this.activatedRoute.snapshot.params['id'];
    this.personaService.update(id, this.persona).subscribe(
      data => {this.router.navigate(['']);
    }, err =>{
      alert("Error al Editar la persona");
      this.router.navigate(['']);
    }
    )

  }
  uploadImage($event:any){
    const id = this.activatedRoute.snapshot.params['id'];
    const name = "perfil_" + id;
    this.imageService.uploadImage($event, name)

  }

}
