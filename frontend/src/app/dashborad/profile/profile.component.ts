import { Component, OnInit } from '@angular/core';
import { BreadcrumbItem } from 'src/app/model/IbreadCrumb';
import { User } from 'src/app/model/user.model';
import { UserService } from 'src/app/service/user.service';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
    user: User | null = null;
    changePassword = false;
    selectedFile: File | null = null;
    imageUrl: string = 'assets/img/default-avatar.png';

    breadcrumbItems: BreadcrumbItem[] = [
        { label: 'Accueil', link: '/' },
        { label: 'Mon Profil' }
    ];

    constructor(private userService: UserService) { }

    ngOnInit() {
        // TODO: Get current user ID from auth service
        const userId = 1; // temporary
        this.loadUserProfile(userId);
        if (this.user) {
            this.imageUrl = this.userService.getProfileImageUrl(this.user.id);
        }
    }

    loadUserProfile(userId: number) {
        this.userService.getById(userId).subscribe(data => {
            this.user = data as User;
        });
    }

    updateProfile(value: any) {
        if (!this.user) return;

        const updatedUser: User = {
            ...this.user,
            username: value.username,
            email: value.email,
            date_modification: new Date()
        };

        this.userService.update(updatedUser).subscribe({
            next: () => {
                this.user = updatedUser;
                ($('#editProfile') as any).modal('hide');
            },
            error: (error) => {
                console.error('Erreur lors de la mise à jour du profil:', error);
            }
        });
    }

    updatePassword(value: any) {
        if (!this.user || value.newPassword !== value.confirmPassword) {
            return;
        }

        const updatedUser: User = {
            ...this.user,
            password: value.newPassword,
            date_modification: new Date()
        };

        this.userService.update(updatedUser).subscribe({
            next: () => {
                this.changePassword = false;
                ($('#changePassword') as any).modal('hide');
            },
            error: (error) => {
                console.error('Erreur lors du changement de mot de passe:', error);
            }
        });
    }

    onFileSelected(event: any) {
        const file = event.target.files[0];
        if (file) {
            // Vérification du type de fichier
            if (!file.type.startsWith('image/')) {
                alert('Veuillez sélectionner une image');
                return;
            }
            // Vérification de la taille (5MB max)
            if (file.size > 5 * 1024 * 1024) {
                alert('L\'image ne doit pas dépasser 5MB');
                return;
            }
            this.selectedFile = file;
            this.uploadImage();
        }
    }

    uploadImage() {
        if (!this.selectedFile || !this.user) return;

        this.userService.uploadProfileImage(this.user.id, this.selectedFile).subscribe({
            next: (response) => {
                // Mise à jour de l'URL de l'image
                this.imageUrl = this.userService.getProfileImageUrl(this.user!.id) + '?t=' + new Date().getTime();
                this.selectedFile = null;
            },
            error: (error) => {
                console.error('Erreur lors de l\'upload de l\'image:', error);
                alert('Erreur lors de l\'upload de l\'image');
            }
        });
    }
} 